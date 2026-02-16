package tech.arkraft.qwerty

import android.app.Application
import android.util.Log
import tech.arkraft.qwerty.net.ArtiTorManager
import tech.arkraft.qwerty.nostr.RelayDirectory
import tech.arkraft.qwerty.ui.theme.ThemePreferenceManager

class BitchatApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        
        // Initialize logging
        Log.i("BitchatApplication", "Application starting...")
        
        // Initialize configuration and preferences managers
        try { tech.arkraft.qwerty.ui.debug.DebugPreferenceManager.init(this) } catch (_: Exception) { }
        try { tech.arkraft.qwerty.service.MeshServicePreferences.init(this) } catch (_: Exception) { }
        ThemePreferenceManager.init(this)
        
        // Initialize background mesh services
        try { tech.arkraft.qwerty.service.MeshForegroundService.start(this) } catch (_: Exception) { }
        
        // Initialize registry components for geohash and favorites
        try {
            tech.arkraft.qwerty.nostr.GeohashConversationRegistry.initialize(this)
            tech.arkraft.qwerty.nostr.GeohashAliasRegistry.initialize(this)
            tech.arkraft.qwerty.favorites.FavoritesPersistenceService.initialize(this)
            tech.arkraft.qwerty.nostr.LocationNotesInitializer.initialize(this)
        } catch (e: Exception) {
            Log.e("BitchatApplication", "Failed to initialize registries", e)
        }

        // Setup networking (Arti/Tor if available)
        try {
            ArtiTorManager.getInstance().initialize(this)
            RelayDirectory.initialize(this)
        } catch (e: Exception) {
            Log.e("BitchatApplication", "Failed to initialize network stack", e)
        }
        
        // Ensure identity is prepared
        try {
            tech.arkraft.qwerty.nostr.NostrIdentityBridge.getCurrentNostrIdentity(this)
        } catch (e: Exception) {
            Log.e("BitchatApplication", "Failed to initialize identity", e)
        }
    }
}
