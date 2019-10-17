/*
 * Copyright (c) 2019 VMware, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, without warranties or
 * conditions of any kind, EITHER EXPRESS OR IMPLIED.  See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.huddle.http;

import java.net.URI;
import java.util.Map;

import org.springframework.web.util.UriComponentsBuilder;

public class UriUtils {

    private UriUtils() {
    }

    public static URI buildUri(String uri, String path) {
        return UriComponentsBuilder.fromUriString(uri).path(path).build().toUri();
    }

    public static URI buildUri(String uri, String path, Map<String, ?> variables) {
        return UriComponentsBuilder.fromUriString(uri).path(path).buildAndExpand(variables).toUri();
    }
}
