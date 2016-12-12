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

package org.plexian.grumy;

public final class FPS {
    /** Frame interval. **/
    public static float frameInterval = 0;

    /** Last frame's time. **/
    // private static long frameTime = 0;

    /** Hold the time from the last frame. **/
    // private static long lastTime = 0;

    /** Fps number. **/
    private static int fps = 0;

    /** Old fps number. **/
    private static int old_fps = 0;

    private static float timeAccum = 0;

    /**
     * FPSCounter constructor. Cannot be instantiated.
     */

    private FPS() {
    }

    /**
     * Calculate the frame rate.
     * 
     * @return The FPS number.
     */

    public final static void update(float elapsedTime) {
        timeAccum += elapsedTime;
        fps++;

        if (timeAccum > 1) {
            timeAccum = 0;
            old_fps = fps;
            fps = 0;
        }
    }

    public final static int get() {
        return old_fps;
    }
}
