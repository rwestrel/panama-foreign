/*
 * Copyright (c) 2015, 2019, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

/*
 * @test
 * @bug 8076318
 * @summary split verifier needs to add class resolution tracing
 * @modules java.base/jdk.internal.misc
 * @library /test/lib
 */

import jdk.test.lib.process.ProcessTools;
import jdk.test.lib.process.OutputAnalyzer;

// Test that the verifier outputs the classes it loads if -Xlog:class+resove=debug is specified"
public class TraceClassRes {
  public static void main(String[] args) throws Exception {

    ProcessBuilder pb = ProcessTools.createJavaProcessBuilder(
        "-Xlog:class+resolve=debug", "-verify", "-Xshare:off", "-version");

    OutputAnalyzer output = new OutputAnalyzer(pb.start());
    output.shouldContain("[class,resolve] java.lang.ClassLoader java.lang.Throwable ClassLoader.java (verification)");
    output.shouldHaveExitValue(0);
  }
}
