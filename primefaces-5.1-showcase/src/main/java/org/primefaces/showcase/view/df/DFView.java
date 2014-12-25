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
package org.primefaces.showcase.view.df;

import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.showcase.domain.Car;

@ManagedBean(name = "dfView")
@SessionScoped	// works
//@ViewScoped			// not working
public class DFView {
    
    // 11/19/14 Jon - added the following two fields
    String type;
    String message;

    public void viewCars() {
        RequestContext.getCurrentInstance().openDialog("viewCars");
    }
    
    public void viewCarsCustomized() {
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("draggable", false);
        options.put("resizable", false);
        options.put("contentHeight", 320);
        
        RequestContext.getCurrentInstance().openDialog("viewCars", options, null);
    }
    
    public void chooseCar() {
        RequestContext.getCurrentInstance().openDialog("selectCar");
    }
    
    public void onCarChosen(SelectEvent event) {
        Car car = (Car) event.getObject();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Car Selected", "Id:" + car.getId());
        
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void showMessage() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "What we do in life", "Echoes in eternity.");
        
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

    // 11/19/14 Jon - added the following getters and setters
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void showConfirmationDialog() {
        RequestContext.getCurrentInstance().openDialog("dlgConfirmation");
    }

    public void noAction() {
        RequestContext.getCurrentInstance().closeDialog("dlgConfirmation");
    }

    public void yesAction() {
        RequestContext.getCurrentInstance().closeDialog("dlgConfirmation");
        if ("Update".equals(type)) {
            int i = 100;
            int j = i;
            // process update request 
        } else if ("Delete".equals(type)) {
            int i = 100;
            int j = i;
            // process delete request 
        }
    }
}
