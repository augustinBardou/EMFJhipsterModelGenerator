package org.eclipse.emf.jhipster.generator.templates.model;

import org.eclipse.emf.codegen.ecore.genmodel.*;
import org.eclipse.emf.jhipster.generator.JhipsterGeneratorUtil;

public class JhipsterModels
{
  protected static String nl;
  public static synchronized JhipsterModels create(String lineSeparator)
  {
    nl = lineSeparator;
    JhipsterModels result = new JhipsterModels();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "// Generated by Jhipster EMF Generator plugins" + NL + "// Author: Stephane Mangin <stephane.mangin@freesbee.fr>" + NL + "// Github: https://github.com/StephaneMangin/EMFJhipsterModelGenerator.git" + NL + "// Timestamp: ";
  protected final String TEXT_2 = NL;
  protected final String TEXT_3 = NL + NL + "{";
  protected final String TEXT_4 = NL + "\t";
  protected final String TEXT_5 = NL + "\t\t\"relationships\" : [" + NL + "\t\t";
  protected final String TEXT_6 = NL + "\t\t\t{" + NL + "\t\t\t\t\"relationshipId\": ";
  protected final String TEXT_7 = "," + NL + "\t\t\t\t\"relationshipName\": \"";
  protected final String TEXT_8 = "\"," + NL + "\t\t\t\t\"otherEntityName\": \"";
  protected final String TEXT_9 = "\"," + NL + "\t\t\t\t\"relationshipType\": \"";
  protected final String TEXT_10 = "\"," + NL + "\t\t\t\t\"otherEntityField\": \"name\"" + NL + "\t\t\t}," + NL + "\t\t" + NL + "\t\t";
  protected final String TEXT_11 = NL + "\t\t]," + NL + "\t\t";
  protected final String TEXT_12 = "if (!fields.isEmpty()) {%>" + NL + "\t\t\"fields\" : [" + NL + "\t\t";
  protected final String TEXT_13 = NL + "\t\t\t{" + NL + "\t\t\t\t\"name\": \"";
  protected final String TEXT_14 = "\"," + NL + "\t\t\t\t\"type\": \"";
  protected final String TEXT_15 = "\"" + NL + "\t\t\t\t";
  protected final String TEXT_16 = NL + "\t\t\t\t}" + NL + "\t\t\t\t";
  protected final String TEXT_17 = NL + "\t\t\t}," + NL + "\t\t\t";
  protected final String TEXT_18 = NL + "\t\t" + NL + "\t\t";
  protected final String TEXT_19 = NL + "\t\t]," + NL + "\t\t";
  protected final String TEXT_20 = NL + "\t\"changelog\": \"";
  protected final String TEXT_21 = "\"," + NL + "    \"dto\": \"mapstruct\"," + NL + "    \"pagination\": \"infinite-scroll\"" + NL + "}";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    GenClass genClass = (GenClass)argument;
	GenPackage genPackage = genClass.getGenPackage();
	GenModel genModel = genPackage.getGenModel();
    java.sql.Timestamp timestamp = new java.sql.Timestamp(new java.util.Date().getTime());
    stringBuffer.append(TEXT_1);
    stringBuffer.append(timestamp);
    stringBuffer.append(TEXT_2);
    genModel.markImportLocation(stringBuffer);
    stringBuffer.append(TEXT_3);
    for (GenClassifier genClassifier : genPackage.getGenClassifiers()) {
    stringBuffer.append(TEXT_4);
    if (genClassifier instanceof GenClass) {
		java.util.List<GenFeature> relations = new java.util.ArrayList<GenFeature>();
		java.util.List<GenFeature> fields = new java.util.ArrayList<GenFeature>();
		for (GenFeature genFeature : genClass.getGenFeatures()) {
		if (genFeature.isReferenceType()){
			relations.add(genFeature);
		} else if (genFeature.isField()) {
			fields.add(genFeature);
		}
		
		if (!relations.isEmpty()) {
    stringBuffer.append(TEXT_5);
    
		for (GenFeature relation : relations) {
    stringBuffer.append(TEXT_6);
    stringBuffer.append(relations.indexOf(genFeature));
    stringBuffer.append(TEXT_7);
    stringBuffer.append(relation.getName());
    stringBuffer.append(TEXT_8);
    stringBuffer.append(relation.getGenClass().getName().toLowerCase());
    stringBuffer.append(TEXT_9);
    stringBuffer.append(JhipsterGeneratorUtil.getTypeExpression(relation));
    stringBuffer.append(TEXT_10);
    	}
    stringBuffer.append(TEXT_11);
    }
    stringBuffer.append(TEXT_12);
    
		for (GenFeature field : fields) {
    stringBuffer.append(TEXT_13);
    stringBuffer.append(field.getName());
    stringBuffer.append(TEXT_14);
    stringBuffer.append(JhipsterGeneratorUtil.getCLassifier(field));
    stringBuffer.append(TEXT_15);
     if ((fields.size()) - 1 == fields.indexOf(field)) {
    stringBuffer.append(TEXT_16);
    } else {
    stringBuffer.append(TEXT_17);
    }
    stringBuffer.append(TEXT_18);
    }
    stringBuffer.append(TEXT_19);
    }	
	}
}
    stringBuffer.append(TEXT_20);
    stringBuffer.append(timestamp);
    stringBuffer.append(TEXT_21);
    return stringBuffer.toString();
  }
}
