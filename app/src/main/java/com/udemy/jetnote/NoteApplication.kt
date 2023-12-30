package com.udemy.jetnote

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * This gives Hilt access to the entire app which creates a dependency container to the top level app.
 *
 * In the manifest file, we added the android:name=".NoteApplication" to it so that this file is registered
 * as the dependency container.
 *
 * In the MainActivity we made the annotation "@AndroidEntryPoint which makes the main activity the
 * dependency container.
 */
@HiltAndroidApp
class NoteApplication : Application() {

}