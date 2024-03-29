/*
 * Copyright 2009-2014 PrimeTek.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.primefaces.showcase.view.ajax;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

/**
 * http://stackoverflow.com/questions/7221495/pass-parameter-to-premotecommand-from-javascript
 * 
 */
@ManagedBean
public class RemoteCommandView {

    public void execute() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Executed", "Using RemoteCommand."));
    }

    // Test URL: http://localhost/showcase-5.4/ui/ajax/remoteCommandJ.xhtml
    // Pass parameter to p:remoteCommand from JavaScript
    // added the following

    public void beanMethod() {
        // retrieve values inside beanMethod
        String model1 = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("model");
        String year1 = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("year");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Executed", 
            "Using RemoteCommand with parameters model := " + model + ", year := " + year));
    }

    @ManagedProperty("#{param.model}")
    private String model;

    @ManagedProperty("#{param.year}")
    private int year;

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(int year) {
        this.year = year;
    }

}
