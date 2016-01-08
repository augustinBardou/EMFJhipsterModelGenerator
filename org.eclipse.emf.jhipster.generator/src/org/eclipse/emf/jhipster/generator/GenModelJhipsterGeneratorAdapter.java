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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenBaseGeneratorAdapter;
import org.eclipse.emf.codegen.jet.JETEmitter;
import org.eclipse.emf.codegen.jet.JETException;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

public class GenModelJhipsterGeneratorAdapter extends GenBaseGeneratorAdapter {
	protected static final int MODEL_DESCRIPTION_ID = 0;

	protected static final JETEmitterDescriptor[] JET_EMITTER_DESCRIPTORS = {
			new JETEmitterDescriptor("model/JhipsterUpdateModel.shjet",
					"org.eclipse.emf.jhipster.generator.templates.model.JhipsterUpdateModel") };

	protected JETEmitterDescriptor[] getJETEmitterDescriptors() {
		return JET_EMITTER_DESCRIPTORS;
	}

	public GenModelJhipsterGeneratorAdapter() {
		super();
	}

	public GenModelJhipsterGeneratorAdapter(GeneratorAdapterFactory generatorAdapterFactory) {
		super(generatorAdapterFactory);
	}

	@Override
	public boolean canGenerate(Object object, Object projectType) {
		return MODEL_PROJECT_TYPE.equals(projectType) ? super.canGenerate(object, projectType) : false;
	}

	/**
	 * Converts the given {@link EObject} model into its XMI representation.
	 * 
	 * @param model
	 *            The model to convert
	 * @return The XMI string representation of the given model
	 */
	private String serializeEObjectToXMI(final EObject model) {
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
		Resource resource = resourceSet.createResource(org.eclipse.emf.common.util.URI.createFileURI("temp/model.xmi"));
		EcoreUtil.resolveAll(resource);
		if (model != null) {
			resource.getContents().add(model);
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			resource.save(baos, null);
		} catch (IOException ioe) {
			//
		}
		String modelXMI = baos.toString();
		try {
			baos.close();
		} catch (IOException ioe) {
		}
		if (model != null) {
			resource.getContents().remove(model);
		}
		try {
			resource.delete(null);
		} catch (IOException e) {
			//
		}
		return modelXMI;
	}

	@Override
	protected Diagnostic generateModel(Object object, Monitor monitor) {
		GenModel genModel = (GenModel) object;

		monitor.beginTask("", 2);
		message = JhipsterGeneratorPlugin.INSTANCE.getString("GeneratingModelDescription.message");
		monitor.subTask(message);

		ensureProjectExists(genModel.getModelDirectory(), genModel, MODEL_PROJECT_TYPE, genModel.isUpdateClasspath(),
				createMonitor(monitor, 1));

		String scriptFile = genModel.getModelProjectDirectory() + "/jhipster/jhipster-models-update.sh";
		generateText(
				scriptFile,
				getJETEmitter(getJETEmitterDescriptors(), MODEL_DESCRIPTION_ID),
				null,
				true,
				getEncoding(URI.createURI(scriptFile)),
				createMonitor(monitor, 1));
		
		String xmiFile = genModel.getModelProjectDirectory() + "/jhipster/models.xmi";
		File file = new File(xmiFile);
		try {
			FileWriter fileWriter = new FileWriter(file, false);
			fileWriter.write(serializeEObjectToXMI(genModel.eContainer()));
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			//
		}
		
		return Diagnostic.OK_INSTANCE;
	}
	
	@Override
	protected void addBaseTemplatePathEntries(List<String> templatePath) {
		templatePath.add(JhipsterGeneratorUtil.TEMPLATE_LOCATION);
		super.addBaseTemplatePathEntries(templatePath);
	}

	@Override
	protected void addClasspathEntries(JETEmitter jetEmitter) throws JETException {
		super.addClasspathEntries(jetEmitter);
		jetEmitter.addVariable(JhipsterGeneratorUtil.CLASSPATH_VARIABLE_NAME, JhipsterGeneratorPlugin.ID);
	}
}