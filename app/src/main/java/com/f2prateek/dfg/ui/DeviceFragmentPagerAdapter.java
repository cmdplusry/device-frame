/*
 * Copyright 2014 Prateek Srivastava (@f2prateek)
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.f2prateek.dfg.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;
import com.f2prateek.dfg.model.Device;
import com.f2prateek.dfg.ui.fragments.DeviceFragment;
import java.util.List;

public class DeviceFragmentPagerAdapter extends FragmentStatePagerAdapter {

  private final List<Device> devices;

  public DeviceFragmentPagerAdapter(FragmentManager fm, List<Device> devices) {
    super(fm);
    this.devices = devices;
  }

  @Override
  public Fragment getItem(int position) {
    return DeviceFragment.newInstance(devices.get(position));
  }

  @Override
  public int getCount() {
    return devices.size();
  }

  public int getDeviceIndex(String id) {
    for (int i = 0, size = devices.size(); i < size; i++) {
      Device device = devices.get(i);
      if (device.id().compareTo(id) == 0) {
        return i;
      }
    }
    return 0;
  }

  @Override public CharSequence getPageTitle(int position) {
    return devices.get(position).name();
  }
}
