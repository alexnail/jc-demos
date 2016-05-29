package com.jc.log;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.jc.commons.FileUtils;
import com.jc.log.bean.ThreadTaskTag;
import com.jc.log.bean.WorkDefinition;
import com.jc.log.bean.WorkStatistic;
import com.jc.log.io.LogFile;

public class WorkerAnalysis {

	private static String BEGIN = "BEGIN";
	private static String END = "END";
	private static String FIELD_DELIM = " "; // 每条记录里的字段是以什么间隔
	private static String TIME_POS = "time_pos"; // 记录时间的位置，从0开始计算
	private static String THREAD_POS = "thread_pos"; // 线程名的位置，从0开始计算
	private static String TEXT_POS = "text_pos"; // 正文的位置，从0开始计算
	private static Map<String, String> CONFIG;
	private List<WorkDefinition> workDefinitions = null;
	private List<ThreadTaskTag> tagBucket = new ArrayList<>(); //未成双的tag放入此集合中 ,重启服务器或异常或存在开始tag不存结束的tag
	private List<ThreadTaskTag> tagBucket4Couple = new ArrayList<>();  //已匹配成双的tag放入此集合中，所以这个集合数量一定是 size%2==0
	private List<ThreadTaskTag> tagBucket4Single = new ArrayList<>();  //未找到前者的结束的tag将会放入此集合中，一般情况是不会有，除非结束的tag比开始的tag先输出

	List<WorkStatistic> statistic = new ArrayList<>(); // 最终产物
	private LogFile logFile;

	public WorkerAnalysis(List<WorkDefinition> workDefinitions, LogFile logFile) {
		this.workDefinitions = workDefinitions;
		this.logFile = logFile;
	}
	
	public void parserLogDir(){
		Map<String,File[]> serverAndFile = logFile.getServerAndFiles();
		for(Entry<String, File[]> saf :serverAndFile.entrySet()){
			String serverId = saf.getKey();
			File[] logs = saf.getValue();
			for(File log:logs){
				try {
					String fullText = FileUtils.read(log);
					String fileName =log.getName();
					parseFullText(fullText, fileName, serverId);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void parseFullText(String fullText, String fileName,String serverId){
		String[] records = fullText.split("\n");
		for(String record:records){
			analysis(record, fileName, serverId);
		}
	}
	
	public void analysis(String record, String fileName,String serverId) {
		String[] field = record.split(FIELD_DELIM);
		String beginTime = field[Integer.valueOf(CONFIG.get(TIME_POS))];
		String workName = field[Integer.valueOf(CONFIG.get(THREAD_POS))];
		StringBuffer textSB = new StringBuffer();
		int textPos =  Integer.valueOf(CONFIG.get(TEXT_POS));
		String delim = "";
		for (int index = textPos; index < field.length; index++) {
			textSB.append(delim).append(field[index]);
			delim = FIELD_DELIM;
		}
		String text = textSB.toString(); // 日志正文
		WorkDefinition workType = null;
		String tag = null;
		String correspondingSlogan = null;
		for(WorkDefinition workDefinition:workDefinitions){
			if (text.contains(workDefinition.getSloganBegin())) {
				tag = BEGIN;
				correspondingSlogan = workDefinition.getSloganClosing();
				break;
			}
			if (text.contains(workDefinition.getSloganClosing())) {
				tag = END;
				correspondingSlogan = workDefinition.getSloganBegin();
				break;
			}
		}
		

		if (tag == null) // 工作不在定义范围内,或是某工作的其中一部分
			return;
		
		ThreadTaskTag threadTaskTag = new ThreadTaskTag(serverId,beginTime, workName, text, tag, correspondingSlogan, fileName);
		if(BEGIN.equals(tag)){
			tagBucket.add(threadTaskTag);
		}else{
			ThreadTaskTag preTag = findPreTag(threadTaskTag);
			if(preTag == null){
				tagBucket4Single.add(threadTaskTag);
				return;
			}
			tagBucket4Couple.add(preTag);
			tagBucket4Couple.add(threadTaskTag);
		}
	}

	private ThreadTaskTag findPreTag(ThreadTaskTag threadTaskTag) {
		ThreadTaskTag preTag = null;
		for(int i=0;i<tagBucket.size();i++){
			 preTag = tagBucket.get(i);
			 if(isMatch(preTag, threadTaskTag)){
				 tagBucket.remove(i);
				 return preTag;
			 }
		}
		for(int i=0;i<tagBucket4Single.size();i++){
			 preTag = tagBucket.get(i);
			 if(isMatch(preTag, threadTaskTag)){
				 tagBucket.remove(i);
				 return preTag;
			 }
		}
		return null;
	}

	/**
	 * 服务器id相同
	 * 线程名相同
	 * 后者包含前者期望的slogan
	 * @param preTag
	 * @param threadTaskTag
	 * @return
	 */
	private boolean isMatch( ThreadTaskTag preTag,ThreadTaskTag threadTaskTag) {
		if(preTag .getServerId().equals(threadTaskTag.getServerId())){
			if(preTag.getThreadName().equals(threadTaskTag.getThreadName())){
				if(threadTaskTag.getText().equals(preTag.getCorrespondingSlogan())){
					return true;
				}
			}
		}
		return false;
	}

}
