package com.yingzi.web.weixin.resp;

/**
 * @author bulute
 * @date 2014-3-19
 *
 */
public class VoiceaMessage extends BaseMessage {

	private Voice Voice;

	public Voice getVoice() {
		return Voice;
	}

	public void setVoice(Voice voice) {
		Voice = voice;
	}
}
