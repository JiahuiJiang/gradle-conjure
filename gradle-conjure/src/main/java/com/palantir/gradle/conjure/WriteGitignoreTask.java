/*
 * (c) Copyright 2018 Palantir Technologies Inc. All rights reserved.
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
 */

package com.palantir.gradle.conjure;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.OutputFile;
import org.gradle.api.tasks.TaskAction;

public class WriteGitignoreTask extends DefaultTask {
    private Path outputFile;
    private String contents;

    public final void setOutputDirectory(File outputDirectory) {
        this.outputFile = outputDirectory.toPath().resolve(".gitignore");
    }

    public final void setContents(String contents) {
        this.contents = contents;
    }

    @Input
    public final String getContents() {
        return contents;
    }

    @OutputFile
    public final File getOutputFile() {
        return outputFile.toFile();
    }

    @TaskAction
    public final void compileFiles() throws IOException {
        Files.createDirectories(outputFile.getParent());

        Files.write(outputFile, contents.getBytes(StandardCharsets.UTF_8));
    }
}
