package tech.arkraft.qwerty

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import tech.arkraft.qwerty.onboarding.BatteryOptimizationStatus
import tech.arkraft.qwerty.onboarding.BluetoothStatus
import tech.arkraft.qwerty.onboarding.LocationStatus
import tech.arkraft.qwerty.onboarding.OnboardingState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val _onboardingState = MutableStateFlow(OnboardingState.CHECKING)
    val onboardingState: StateFlow<OnboardingState> = _onboardingState.asStateFlow()

    private val _bluetoothStatus = MutableStateFlow(BluetoothStatus.DISABLED)
    val bluetoothStatus: StateFlow<BluetoothStatus> = _bluetoothStatus.asStateFlow()

    private val _locationStatus = MutableStateFlow(LocationStatus.DISABLED)
    val locationStatus: StateFlow<LocationStatus> = _locationStatus.asStateFlow()
    
    private val _batteryOptimizationStatus = MutableStateFlow(BatteryOptimizationStatus.ENABLED)
    val batteryOptimizationStatus: StateFlow<BatteryOptimizationStatus> = _batteryOptimizationStatus.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage.asStateFlow()

    private val _isBluetoothLoading = MutableStateFlow(false)
    val isBluetoothLoading: StateFlow<Boolean> = _isBluetoothLoading.asStateFlow()

    private val _isLocationLoading = MutableStateFlow(false)
    val isLocationLoading: StateFlow<Boolean> = _isLocationLoading.asStateFlow()
    
    private val _isBatteryOptimizationLoading = MutableStateFlow(false)
    val isBatteryOptimizationLoading: StateFlow<Boolean> = _isBatteryOptimizationLoading.asStateFlow()

    fun updateOnboardingState(state: OnboardingState) {
        _onboardingState.value = state
    }

    fun updateBluetoothStatus(status: BluetoothStatus) {
        _bluetoothStatus.value = status
    }

    fun updateLocationStatus(status: LocationStatus) {
        _locationStatus.value = status
    }
    
    fun updateBatteryOptimizationStatus(status: BatteryOptimizationStatus) {
        _batteryOptimizationStatus.value = status
    }

    fun updateErrorMessage(message: String) {
        _errorMessage.value = message
    }

    fun updateBluetoothLoading(isLoading: Boolean) {
        _isBluetoothLoading.value = isLoading
    }

    fun updateLocationLoading(isLoading: Boolean) {
        _isLocationLoading.value = isLoading
    }
    
    fun updateBatteryOptimizationLoading(isLoading: Boolean) {
        _isBatteryOptimizationLoading.value = isLoading
    }
}
