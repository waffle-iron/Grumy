/**
 * Copyright 2016 The Plexian Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.plexian.grumy.launcher;

public enum LauncherMessage {
    ERROR("Whoops! There was an error. The best way to move on is to try again."), 
    SERVER_ERROR("Whoops! There was a glitch in our servers. Try again later :("), 
    INVALID_LOGIN("Darn! It seems there isn't any player with that login :("), 
    VALID_LOGIN("Nice job! Thanks for playing!");

    private String message;

    LauncherMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
