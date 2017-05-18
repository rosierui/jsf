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
package org.primefaces.showcase.view.misc;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class ProgressBarView_J implements Serializable {
    
    private Integer progress;
    private String  message;

    /**
     * <p:progressBar widgetVar="pbAjax" ajax="true" interval="300" value="#{progressBarView_J.progress}" />
     * @return
     */
	public Integer getProgress() { // automatically called by p:progressBar for a given interval
		message = null; 
		if(progress == null) {
			progress = 0;
        }
		else {
			progress = progress + (int)(Math.random() * 35);
			
			if(progress > 100)
				progress = 100;
		}
		
		return progress;
	}

	public void setProgress(Integer progress) {
		this.progress = progress;
	}

	/**
	 * <p:progressBar listener="#{progressBarView_J.onComplete}" />
	 */
	public void onComplete() {
		progress = null;
		message = "Calculation complete!"; 
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Progress Completed"));
	}
    
	/**
	 * <p:commandButton value="Cancel" actionListener="#{progressBarView_J.cancel}" /> 
	 */
    public void cancel() {
        progress = null;
    }

    // ========================================================================
    // new methods added below
    /**
	   <p:commandButton value="Start1" actionListener="#{progressBarView_J.start}" 
	       onclick="PF('pbAjax').start();PF('startButton1').disable();" widgetVar="startButton1" >
	   </p:commandButton>
	   this calls progressBarView_J.start method

	   The following two with type="button" does not call progressBarView_J.onStart method (w/ or w/o <p:ajax/>)
       <p:commandButton value="Start" type="button" actionListener="#{progressBarView_J.onStart}" 
        		onclick="PF('pbAjax').start();PF('startButton2').disable();" widgetVar="startButton2" >
       </p:commandButton>

       <p:commandButton value="Start" type="button" actionListener="#{progressBarView_J.onStart}" 
        		onclick="PF('pbAjax').start();PF('startButton2').disable();" widgetVar="startButton2" >
			<p:ajax event="click" update="pbar calcMessage " listener="#{progressBarView_J.calculate()}" />
       </p:commandButton>
     */
	public void start() {
		message = "Calculation started..."; 
	}

	public String getStyleClass() {
        if (progress < 100) {
            return "animated";
        } else {
            return "";
        }
    }

    public String getMessage() {
        return message;
    }

    /**
     * <p:commandButton value="Start" type="button" actionListener="#{progressBarView_J.onStart()}" widgetVar="startButton2" >
     *   <p:ajax event="click" update="pbar calcMessage " listener="#{progressBarView_J.calculate()}" />
     * </p:commandButton>
     */
    public void calculate() {
		message = "enter calculate()..."; 
    }
}
