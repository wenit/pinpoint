/*
 * Copyright 2016 NAVER Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.navercorp.pinpoint.test.plugin;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Woonduk Kang(emeroad)
 */
public class PluginTestClassLoader extends URLClassLoader {

    public PluginTestClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    public PluginTestClassLoader(URL[] urls) {
        super(urls);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        // override for debugging
        return super.loadClass(name);
    }

    public static ClassLoader getClassLoader(List<File> fileList) {
        URL[] urlList = getUrlList(fileList);
        return new PluginTestClassLoader(urlList);
    }

    private static URL[] getUrlList(List<File> fileList)  {
        if (fileList == null) {
            return new URL[0];
        }

        final List<URL> urls = new ArrayList<URL>();
        for (File file : fileList) {
            final URL url = toURL(file);
            urls.add(url);
        }
        return urls.toArray(new URL[0]);
    }

    private static URL toURL(File file) {
        try {
            return file.toURI().toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e. getMessage(), e);
        }
    }


}
