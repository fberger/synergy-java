package com.github.fberger.synergy;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;

public class SynergyChannelPipelineFactory implements ChannelPipelineFactory {

	@Override
	public ChannelPipeline getPipeline() throws Exception {
		ChannelPipeline pipeline = Channels.pipeline();
		pipeline.addLast("decoder", new SynergyMessageDecoder());
		pipeline.addLast("encoder", new SynergyMessageEncoder());
		pipeline.addLast("handler", new SynergyServerHandler());
		return pipeline;
	}

}
