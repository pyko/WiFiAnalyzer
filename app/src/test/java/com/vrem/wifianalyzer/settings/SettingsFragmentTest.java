/*
 * WiFiAnalyzer
 * Copyright (C) 2019  VREM Software Development <VREMSoftwareDevelopment@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.vrem.wifianalyzer.settings;

import android.os.Build;

import com.vrem.wifianalyzer.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.support.v4.SupportFragmentController;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
public class SettingsFragmentTest {
    private SettingsFragment fixture;

    @Before
    public void setUp() {
//        RobolectricUtil.INSTANCE.getActivity();
    }

    @Test
    public void testOnCreate() {
        // setup
        fixture = SupportFragmentController.setupFragment(new SettingsFragment());
        // validate
        assertNotNull(fixture.getView());
    }

    @Test
    public void testScanIntervalIsInvisible() {
        // setup
        fixture = SupportFragmentController.setupFragment(new SettingsFragment());
        String scanIntervalKey = fixture.getString(R.string.scan_interval_key);
        // validate
        assertFalse(fixture.findPreference(scanIntervalKey).isVisible());
    }

    @Test
    public void testScanFastIsVisible() {
        // setup
        fixture = SupportFragmentController.setupFragment(new SettingsFragment());
        String scanFastKey = fixture.getString(R.string.scan_fast_key);
        // validate
        assertTrue(fixture.findPreference(scanFastKey).isVisible());
    }

    @Config(sdk = Build.VERSION_CODES.O)
    @Test
    public void testScanIntervalIsVisible() {
        // setup
        fixture = SupportFragmentController.setupFragment(new SettingsFragment());
        String scanIntervalKey = fixture.getString(R.string.scan_interval_key);
        // validate
        assertTrue(fixture.findPreference(scanIntervalKey).isVisible());
    }

    @Config(sdk = Build.VERSION_CODES.O)
    @Test
    public void testScanFastIsInvisible() {
        // setup
        fixture = SupportFragmentController.setupFragment(new SettingsFragment());
        String scanFastKey = fixture.getString(R.string.scan_fast_key);
        // validate
        assertFalse(fixture.findPreference(scanFastKey).isVisible());
    }
}