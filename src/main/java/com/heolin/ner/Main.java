package com.heolin.ner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.heolin.ner.training.TrainingDataFactory;

/**
 * Hello world!
 *
 */
public class Main 
{
    public static void main( String[] args )
    {
    	Options options = new Options();
    	options.addOption( "pt", "parse-train", false, "Parses input into .ann format." );
    	options.addOption( "st", "statistical", false, "Statistical" );
    	
    	Annotator annatator = new Annotator();

        CommandLineParser parser = new BasicParser();
        try {
            CommandLine line = parser.parse( options, args );

            try{
    			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            	//BufferedReader br = new BufferedReader(new FileReader(new File("/home/heolin123/Documents/NLP/NER_Tagging/sentences.out")));
    			String input;
    			String result;
    	 
    			while((input = br.readLine()) != null){
    				if (line.hasOption("pt"))
    				{
	    				result = TrainingDataFactory.get().getTrainingData(input);
    				} else if (line.hasOption("st")) {
    					result = annatator.annotate(input, true);
    				} else {
    					result = annatator.annotate(input);
    				}
    				System.out.println(result);
    			}
    	 
    		}catch(IOException io){
    			io.printStackTrace();
    		}	
        }
        catch( ParseException exp ) {
            System.err.println( "Parsing failed.  Reason: " + exp.getMessage() );
        }
    }
}
