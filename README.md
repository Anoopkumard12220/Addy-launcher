# ADDY — Personal Android Launcher

**YOUR DEVICE. YOUR RULES.**

ADDY is a minimal, pure-dark personal Android launcher built for Anoop Dubey. Designed to feel personal, premium, calm, elegant, fast and customizable — with an original design language (not a copy of iOS, Nothing OS, or Pixel Launcher).

## Technology
- Kotlin + Jetpack Compose (Material 3)
- Android SDK, API 24 (min) → API 36 (target, Android 16)
- Gradle Kotlin DSL
- MVVM, Kotlin Coroutines & Flow
- DataStore for persistent preferences
- Android PackageManager for app discovery
- No ads, no analytics, no account, no network required

## Project structure
```
app/src/main/java/com/addy/launcher/
  MainActivity.kt                 # Launcher activity (HOME intent, edge-to-edge)
  apps/                           # App discovery (PackageManager)
    AppInfo.kt, AppRepository.kt, PackageChangeReceiver.kt
  search/                         # Offline app search
    SearchViewModel.kt, SearchState.kt
  settings/                       # Persistent customization (DataStore)
    LauncherDataStore.kt, SettingsViewModel.kt
  gestures/                       # Swipe up → drawer, swipe down → search
    GestureHost.kt
  ui/
    root/                         # Root host + overlays (drawer/search/settings/edit)
      LauncherRoot.kt
    home/                         # Home screen (clock, app grid, dock)
      HomeScreen.kt, Clock.kt, AppGrid.kt, Dock.kt
    drawer/                       # App drawer
      AppDrawerScreen.kt
    edit/                         # Home edit mode
      EditModeHost.kt
    settings/                     # Settings screen
      SettingsScreen.kt
    theme/                        # Custom dark design system
      AddyTheme.kt, Typography.kt
  utils/
    AppIcon.kt
```

## Build
The project uses Gradle Kotlin DSL and targets Android 16 (API 36). A compatible JDK (17), Android SDK (build-tools for API 36), and the Gradle Wrapper are required.

```bash
./gradlew :app:assembleDebug
```

The debug APK is produced at:
`app/build/outputs/apk/debug/app-debug.apk`

## Installation
1. Build the debug APK (`./gradlew :app:assembleDebug`).
2. Transfer `app-debug.apk` to an Android device.
3. Install it (enable "Install unknown apps" for the source if prompted).
4. Open Settings → Apps → Default apps → Launcher and select **ADDY**.
5. To revert, choose another launcher in the same settings screen.

ADDY works fully offline and requires no user registration or network access.

## GitHub Actions
`.github/workflows/android-build.yml` builds the debug APK on push to `main`, on pull requests, and on manual dispatch, and uploads the APK as a workflow artifact. Release builds sign through GitHub Secrets (`ANDROID_KEYSTORE_B64`, `ANDROID_KEYSTORE_PASSWORD`, `ANDROID_KEY_ALIAS`, `ANDROID_KEY_PASSWORD`) and are never logged.

## Known limitations (MVP)
- Icon rendering uses the platform `ImageView` fallback when an app icon cannot be resolved.
- Home edit mode (rearrange/remove) is scaffolded; full drag-and-drop reorder is a post-MVP item.
- Widgets, icon packs, backup/restore, weather, and AI search are future features and are not implemented in v1.
- Build verification was not performed in this environment (no Java/Gradle/Android SDK toolchain present); compile the project with a compatible toolchain before release.

## Android 16
Target SDK is API 36. Edge-to-edge is enabled, system bar insets are handled via transparent status/navigation bars, and the activity supports orientation/screen-size changes for phones and tablets.
