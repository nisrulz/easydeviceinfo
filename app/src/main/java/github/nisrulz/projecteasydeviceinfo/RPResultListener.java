/*
 * Copyright (c) 2015 - 2016 OmniLabs, Inc.
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 * All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package github.nisrulz.projecteasydeviceinfo;

public interface RPResultListener {
  void onPermissionGranted();

  void onPermissionDenied();
}
