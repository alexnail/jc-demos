package com.jc.demo.netty;

import java.util.Date;

import com.ibm.icu.text.SimpleDateFormat;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class EchoClentHandler extends SimpleChannelInboundHandler<ByteBuf> {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:sss");
	
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("sending a message");
		ctx.writeAndFlush(Unpooled.copiedBuffer("JC rocks!",CharsetUtil.UTF_8));
	}


	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
		String threadName= Thread.currentThread().getName();
		String logTime = sdf.format(new Date());
		System.out.println(logTime+":["+threadName+"] Client received:"+ByteBufUtil.hexDump(in.readBytes(in.readableBytes())));
	}


	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		String threadName= Thread.currentThread().getName();
		String logTime = sdf.format(new Date());
		System.out.print(logTime+":["+threadName+"] ");
		cause.printStackTrace();
		ctx.close();
	}

	 

}
