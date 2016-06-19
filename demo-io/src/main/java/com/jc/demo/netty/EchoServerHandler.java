package com.jc.demo.netty;

import java.util.Date;

import com.ibm.icu.text.SimpleDateFormat;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class EchoServerHandler extends ChannelInboundHandlerAdapter {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:sss");
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String threadName= Thread.currentThread().getName();
		String logTime = sdf.format(new Date());
		System.out.println(logTime+":["+threadName+"]Server received:" +msg);
		System.out.println(logTime+":["+threadName+"] Hex:" +ByteBufUtil.hexDump((ByteBuf) msg));
		ctx.write(msg);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
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
