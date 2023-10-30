/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.affirmations.data

import com.example.affirmations.R
import com.example.affirmations.model.APOD

class Datasource() {
    fun loadAffirmations(): List<APOD> {
        return listOf<APOD>(
            APOD(R.string.affirmation1, R.drawable.nasa1),
            APOD(R.string.affirmation2, R.drawable.image2),
            APOD(R.string.affirmation3, R.drawable.image3),
            APOD(R.string.affirmation4, R.drawable.image4),
            APOD(R.string.affirmation5, R.drawable.image5),
            APOD(R.string.affirmation6, R.drawable.image6),
            APOD(R.string.affirmation7, R.drawable.image7),
            APOD(R.string.affirmation8, R.drawable.image8),
            APOD(R.string.affirmation9, R.drawable.image9),
            APOD(R.string.affirmation10, R.drawable.image10))
    }
}
