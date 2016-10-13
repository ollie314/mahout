/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.mahout.common.iterator;

import java.util.Iterator;

/**
 * Wraps an {@link Iterable} whose {@link Iterable#iterator()} returns only some subset of the elements that
 * it would, as determined by a iterator rate parameter.
 */
public final class SamplingIterable<T> implements Iterable<T> {
  
  private final Iterable<? extends T> delegate;
  private final double samplingRate;
  
  public SamplingIterable(Iterable<? extends T> delegate, double samplingRate) {
    this.delegate = delegate;
    this.samplingRate = samplingRate;
  }
  
  @Override
  public Iterator<T> iterator() {
    return new SamplingIterator<>(delegate.iterator(), samplingRate);
  }
  
  public static <T> Iterable<T> maybeWrapIterable(Iterable<T> delegate, double samplingRate) {
    return samplingRate >= 1.0 ? delegate : new SamplingIterable<>(delegate, samplingRate);
  }
  
}
