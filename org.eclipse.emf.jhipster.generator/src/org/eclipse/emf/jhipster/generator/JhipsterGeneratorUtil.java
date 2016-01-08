/**
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.jhipster.generator;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier;
import org.eclipse.emf.codegen.ecore.genmodel.GenOperation;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenParameter;
import org.eclipse.emf.codegen.ecore.genmodel.GenTypedElement;
import org.eclipse.emf.codegen.util.ImportManager;
import org.eclipse.emf.ecore.ETypedElement;

public class JhipsterGeneratorUtil
{
  private JhipsterGeneratorUtil()
  {
    super();
  }

  public final static String TEMPLATE_LOCATION = JhipsterGeneratorPlugin.INSTANCE.getBaseURL().toString() + "templates";

  public final static String CLASSPATH_VARIABLE_NAME = "EMF_JHIPSTER_GENERATOR_VALIDATOR";

  public static String getJhipsterPackageName(GenPackage genPackage)
  {
    String basePackage = genPackage.getInterfacePackageName();
    return basePackage.length() > 0 ? basePackage + ".jhpister" : "Jhipster";
  }

  public static String getJhipsterModelName(GenClass genClass)
  {
    return genClass.getName();
  }

  public static String getQualifiedJhipsterModelName(GenClass genClass)
  {
    return getJhipsterPackageName(genClass.getGenPackage()) + "." + getJhipsterModelName(genClass);
  }

  public static String getImportedJhipsterModelName(GenClass genClass)
  {
    return genClass.getGenModel().getImportedName(getQualifiedJhipsterModelName(genClass));
  }

  protected static String getImportedName(String qualifiedName, ImportManager importManager)
  {
    int index = qualifiedName.indexOf("$");
    importManager.addImport(index == -1 ? qualifiedName : qualifiedName.substring(0, index));
    return importManager.getImportedName(qualifiedName);
  }

  public static String getSuperTypesExpression(GenClass genClass)
  {
    List<GenClass> extendsList = genClass.getBaseGenClasses();
    if (!extendsList.isEmpty())
    {
      StringBuffer result = new StringBuffer();
      result.append(" -> ");
      for (Iterator<GenClass> i = extendsList.iterator(); i.hasNext(); )
      {
        result.append(i.next().getName());
        if (i.hasNext())
        {
          result.append(", ");
        }
      }
      return result.toString();
    }
    return "";
  }

  public static String getCLassifier(GenTypedElement genTypedElement) {
	    GenClassifier genClassifier = genTypedElement.getTypeGenClassifier();
	    return genClassifier == null ? "" : genClassifier.getName();
  }
  
  public static String getTypeExpression(GenTypedElement genTypedElement)
  {
    StringBuffer result = new StringBuffer();
    ETypedElement eTypedElement = (ETypedElement)genTypedElement.getEcoreModelElement();
    int lowerBound = eTypedElement.getLowerBound();
    int upperBound = eTypedElement.getUpperBound();

    if (lowerBound == ETypedElement.UNSPECIFIED_MULTIPLICITY) {
        // ERROR to be thrown, By default one for now
    	result.append("one");
    } else if (lowerBound == ETypedElement.UNBOUNDED_MULTIPLICITY) {
        result.append("many");
    } else {
        result.append("one");
    }
    result.append("-to-");
    if (upperBound == ETypedElement.UNSPECIFIED_MULTIPLICITY) {
        // ERROR to be thrown, By default one for now
    	result.append("one");
    } else if (upperBound == ETypedElement.UNBOUNDED_MULTIPLICITY) {
        result.append("many");
    } else {
        result.append("one");
    }
    return result.toString();
  }

  public static String getParameterExpression(GenOperation genOperation)
  {
    StringBuffer result = new StringBuffer();
    result.append('(');

    for (Iterator<GenParameter> i = genOperation.getGenParameters().iterator(); i.hasNext(); )
    {
      GenParameter genParameter = i.next();
      result.append(getTypeExpression(genParameter));
      if (i.hasNext())
      {
        result.append(", ");
      }
    }
    result.append(')');
    return result.toString();
  }
  
}
