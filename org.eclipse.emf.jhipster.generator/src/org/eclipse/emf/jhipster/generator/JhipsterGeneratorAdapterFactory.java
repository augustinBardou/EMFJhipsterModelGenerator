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

import org.eclipse.emf.codegen.ecore.genmodel.generator.GenModelGeneratorAdapterFactory;
import org.eclipse.emf.common.notify.Adapter;

public class JhipsterGeneratorAdapterFactory extends GenModelGeneratorAdapterFactory
{
  private static final boolean DISABLE = "false".equals(System.getProperty("org.eclipse.emf.jhipster.generator"));

  @Override
  public Adapter createGenPackageAdapter() { return null; }
  
  @Override
  public Adapter createGenEnumAdapter() { return null; }

  @Override
  public Adapter createGenModelAdapter()
  {
    if (genModelGeneratorAdapter == null && !DISABLE)
    {
      genModelGeneratorAdapter = new GenModelJhipsterGeneratorAdapter(this);
    }
    return genModelGeneratorAdapter;
  }

  @Override
  public Adapter createGenClassAdapter()
  {
    if (genClassGeneratorAdapter == null && !DISABLE)
    {
      genClassGeneratorAdapter = new GenClassJhipsterModelsGeneratorAdapter(this);
    }
    return genClassGeneratorAdapter;
  }
}
