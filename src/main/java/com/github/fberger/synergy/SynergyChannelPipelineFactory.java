package com.github.fberger.synergy;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;

public class SynergyChannelPipelineFactory implements ChannelPipelineFactory {

	private final SynergyServer synergyServer;

	public SynergyChannelPipelineFactory(SynergyServer synergyServer) {
		this.synergyServer = synergyServer;
	}

	@Override
	public ChannelPipeline getPipeline() throws Exception {
		ChannelPipeline pipeline = Channels.pipeline();
		pipeline.addLast("decoder", new SynergyMessageDecoder());
		pipeline.addLast("encoder", new SynergyMessageEncoder());
		pipeline.addLast("handler", new SynergyServerHandler(synergyServer));
		return pipeline;
	}

}
