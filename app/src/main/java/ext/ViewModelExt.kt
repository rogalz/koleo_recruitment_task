package ext

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

fun ViewModel.launchIO(task: suspend CoroutineScope.() -> Unit): Job = viewModelScope.launch(Dispatchers.IO) { task() }
