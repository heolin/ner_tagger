package com.heolin.ner;

import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.Annotator;
import edu.stanford.nlp.pipeline.TokensRegexAnnotator;

public class NERRegexAnnotator implements Annotator{

	private TokensRegexAnnotator annotator;
	
	public NERRegexAnnotator()
	{
		Properties prop = new Properties();
		try {
			prop.load(NERRegexAnnotator.class.getResourceAsStream(Strings.REGEX_PROP_PATH));
			annotator = new TokensRegexAnnotator(prop);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void annotate(Annotation annotation) {
		annotator.annotate(annotation);
	}

	public Set<Requirement> requirementsSatisfied() {
		return null;
	}

	public Set<Requirement> requires() {
		return null;
	}

}
