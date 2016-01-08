package org.eclipse.emf.jhipster.generator.templates.model;

import org.eclipse.emf.codegen.ecore.genmodel.*;
import org.eclipse.emf.jhipster.generator.JhipsterGeneratorUtil;

public class JhipsterUpdateModel
{
  protected static String nl;
  public static synchronized JhipsterUpdateModel create(String lineSeparator)
  {
    nl = lineSeparator;
    JhipsterUpdateModel result = new JhipsterUpdateModel();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "#!/bin/sh" + NL + "#---------------------------------------------" + NL + "#   jhipster-models-update" + NL + "#" + NL + "#   Utility script to Update Jhipster's models after beign copied inside the .jhipster models folder" + NL + "#" + NL + "#   Refer to the usage() function below for usage." + NL + "#" + NL + "#   Copyright 2016, Stephane <stephane.mangin@freesbee.fr>" + NL + "#" + NL + "#   LICENSE:" + NL + "#" + NL + "#   Permission is hereby granted, free of charge, to any person obtaining a" + NL + "#   copy of this software and associated documentation files (the \"Software\")," + NL + "#   to deal in the Software without restriction, including without limitation" + NL + "#   the rights to use, copy, modify, merge, publish, distribute, sublicense," + NL + "#   and/or sell copies of the Software, and to permit persons to whom the" + NL + "#   Software is furnished to do so, subject to the following conditions:" + NL + "#" + NL + "#   The above copyright notice and this permission notice shall be included" + NL + "#   in all copies or substantial portions of the Software." + NL + "#" + NL + "#   THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS" + NL + "#   OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY," + NL + "#   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL" + NL + "#   THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR" + NL + "#   OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE," + NL + "#   ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR" + NL + "#   OTHER DEALINGS IN THE SOFTWARE." + NL + "#" + NL + "#---------------------------------------------" + NL + "" + NL + "jhipsterFolder=\".jhipster\"" + NL + "" + NL + "usage()" + NL + "{" + NL + "cat << EOT" + NL + "Name" + NL + "" + NL + "jhipster-models-update - Update Jhipster's models after beign copied inside the .jhipster models folder" + NL + "" + NL + "Synopsis" + NL + "" + NL + "jhipster-models-update [options]" + NL + "" + NL + "jhipster-models-update { --help | --version }" + NL + "" + NL + "Options:" + NL + "" + NL + "-p <dir>,\t\t: Path to your Jhipster folder." + NL + "--path <dir>" + NL + "-f, --force\t\t: Force the complete regeneration of CRUD and DTO classes (if any). Use at your own risk if they have been customized !" + NL + "--help\t\t\t: Show command synopsis." + NL + "--version\t\t: Show the xdg-utils version information." + NL + "" + NL + "Examples" + NL + "" + NL + "jhipster-models-update -f -p ../../MyJhipsterProject" + NL + "" + NL + "EOT" + NL + "}" + NL + "" + NL + "force=" + NL + "path=" + NL + "while [ $# -gt 0 ]; do" + NL + "    parm=\"$1\"" + NL + "    shift" + NL + "" + NL + "    case \"$parm\" in" + NL + "      -p|--path)" + NL + "      \tif [ -z \"$1\" ] ; then" + NL + "            echo \"ERROR: path argument missing for -p\"" + NL + "            exit 1" + NL + "        fi" + NL + "        path=\"$1\"" + NL + "    \tshift" + NL + "        ;;" + NL + "      -f|--force)" + NL + "        force=\" --force\"" + NL + "        shift" + NL + "        ;;" + NL + "      -*)" + NL + "        echo \"ERROR: unexpected option '$parm'\"" + NL + "        exit 1" + NL + "        ;;" + NL + "      *)" + NL + "        echo \"ERROR:  missing option -p\"" + NL + "        exit 1" + NL + "        ;;" + NL + "    esac" + NL + "done" + NL + "" + NL + "" + NL + "if [ ! -z $path ]; then" + NL + "    echo \"Entering: $path\" " + NL + "    cd $path && pwd" + NL + "fi" + NL + "if [ ! -d \".jhipster\" ] ; then" + NL + "    echo \"ERROR: .jhipster folder not found\"" + NL + "    exit 1" + NL + "fi" + NL + "" + NL + "echo \"Models found: `ls -x .jhipster/`\"" + NL + "" + NL + "for f in `ls $jhipsterFolder`; do" + NL + "\techo \"-------------------------------------------------\"" + NL + "\techo \"yo jhipster:entity \"${f%.json}\" $force\"" + NL + "\techo \"-------------------------------------------------\"" + NL + "\tyo jhipster:entity ${f%.json} $force;" + NL + "done" + NL + "" + NL + "exit 0";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    GenModel genModel = (GenModel)argument;
    stringBuffer.append(TEXT_1);
    return stringBuffer.toString();
  }
}
