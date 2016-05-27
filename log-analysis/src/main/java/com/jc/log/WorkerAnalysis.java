package com.jc.log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jc.log.bean.ThreadTaskTag;
import com.jc.log.bean.WorkDefinition;
import com.jc.log.bean.WorkStatistic;

public class WorkerAnalysis {

	private List<WorkDefinition> workDefinitions = null;
	private static String BEGIN = "BEGIN";
	private static String END = "END";
	private static String FIELD_DELIM = " "; // 每条记录里的字段是以什么间隔
	private static String TIME_POS = "time_pos"; // 记录时间的位置，从0开始计算
	private static String THREAD_POS = "thread_pos"; // 线程名的位置，从0开始计算
	private static String TEXT_POS = "text_pos"; // 正文的位置，从0开始计算
	private static Map<String, String> CONFIG;

	List<WorkStatistic> statistic = new ArrayList<>(); // 最终产物

	public WorkerAnalysis(List<WorkDefinition> workDefinitions) {
		this.workDefinitions = workDefinitions;
	}

	public void analysis(String record, String fileName) {
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
		
		ThreadTaskTag threadTaskTag = new ThreadTaskTag(beginTime, workName, text, tag, correspondingSlogan, fileName);
	}

}
