package com.mapbox.services.android.core.permissions;

import java.util.List;

/**
 * Callback used in PermissionsManager
 */

interface PermissionsListener {

  void onExplanationNeeded(List<String> permissionsToExplain);

  void onPermissionResult(boolean granted);
}
