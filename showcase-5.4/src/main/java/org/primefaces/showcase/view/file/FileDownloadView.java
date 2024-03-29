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
package org.primefaces.showcase.view.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
public class FileDownloadView {
    
    private StreamedContent file;
    
    public FileDownloadView() {
//        InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/resources/demo/images/optimus.jpg");
//        InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/resources/demo/images/optimus.jpg");
//        file = new DefaultStreamedContent(stream, "image/jpg", "downloaded_optimus.jpg");
    }

    private String filepath = null;
    
    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public StreamedContent getFile() {
        if (file == null) {
            InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/resources/demo/images/optimus.jpg");
            file = new DefaultStreamedContent(stream, "image/jpg", "downloaded_optimus.jpg");
        }
        return file;
    }

    public StreamedContent getFile2() {
        StreamedContent file2 = null;
        if (file2 == null) {
            try {
                InputStream stream = new FileInputStream("/home/moonwave/Pictures/icon-1.png");
                file2 = new DefaultStreamedContent(stream, "image/png", "my_downloaded.png");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return file2;
    }

    public StreamedContent getFile3() {
        StreamedContent file2 = null;
        if (file2 == null) {
            try {
                InputStream stream = new FileInputStream(this.filepath);
                Path p = Paths.get(this.filepath);
                String filename = p.getFileName().toString();

                File f = new File(this.filepath);
                filename = f.getName();

                filename = this.getFilename(filepath);

                file2 = new DefaultStreamedContent(stream, "image/png", filename);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return file2;
    }

    public void download() {
      // TODO
      RequestContext.getCurrentInstance().execute("PrimeFaces.monitorDownload(start, stop);");
    }
    
    /**
     * Get filename from a full path
     * @param path
     * @return
     */
    public String getFilename(String path) {
    	if (path == null) {
    		return null;
    	}
    	String strPath = path.substring(path.lastIndexOf("/") + 1, path.length());
    	return strPath;
    }
}
