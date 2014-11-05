package com.heolin.ner;

import edu.stanford.nlp.ling.CoreAnnotation;

public class TestAnnotation implements CoreAnnotation<String>{

	public Class<String> getType() {
		return String.class;
	}

}
