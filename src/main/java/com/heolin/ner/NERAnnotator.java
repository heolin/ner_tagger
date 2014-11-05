package com.heolin.ner;

import java.io.FileNotFoundException;
import java.util.Set;

import edu.stanford.nlp.ie.NERClassifierCombiner;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.Annotator;
import edu.stanford.nlp.pipeline.NERCombinerAnnotator;

public class NERAnnotator implements Annotator{

	private NERCombinerAnnotator annotator;
	
	public NERAnnotator(String nerPath)
	{
		NERClassifierCombiner nerCombiner;
		try {
			nerCombiner = new NERClassifierCombiner(false, false, nerPath);
			annotator = new NERCombinerAnnotator(nerCombiner, false);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void annotate(Annotation arg0) {
		annotator.annotate(arg0);
	}

	@Override
	public Set<Requirement> requirementsSatisfied() {
		return null;
	}

	@Override
	public Set<Requirement> requires() {
		return null;
	}

}
