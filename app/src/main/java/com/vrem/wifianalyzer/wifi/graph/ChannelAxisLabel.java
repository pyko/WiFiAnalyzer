/*
 *    Copyright (C) 2015 - 2016 VREM Software Development <VREMSoftwareDevelopment@gmail.com>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.vrem.wifianalyzer.wifi.graph;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v4.util.Pair;

import com.jjoe64.graphview.LabelFormatter;
import com.jjoe64.graphview.Viewport;
import com.vrem.wifianalyzer.wifi.band.WiFiBand;
import com.vrem.wifianalyzer.wifi.band.WiFiChannel;
import com.vrem.wifianalyzer.wifi.band.WiFiChannelCountry;
import com.vrem.wifianalyzer.wifi.graph.wrapper.GraphViewBuilder;

import org.apache.commons.lang3.StringUtils;

import java.util.Locale;

class ChannelAxisLabel implements LabelFormatter {
    private final WiFiBand wiFiBand;
    private final Pair<WiFiChannel, WiFiChannel> bounds;
    private final Resources resources;

    ChannelAxisLabel(@NonNull WiFiBand wiFiBand, @NonNull Pair<WiFiChannel, WiFiChannel> bounds, @NonNull Resources resources) {
        this.wiFiBand = wiFiBand;
        this.bounds = bounds;
        this.resources = resources;
    }

    @Override
    public String formatLabel(double value, boolean isValueX) {
        String result = StringUtils.EMPTY;

        int valueAsInt = (int) (value + (value < 0 ? -0.5 : 0.5));
        if (isValueX) {
            result += findChannel(valueAsInt);
        } else {
            if (valueAsInt <= GraphViewBuilder.MAX_Y && valueAsInt > GraphViewBuilder.MIN_Y) {
                result += valueAsInt;
            }
        }
        return result;
    }

    @Override
    public void setViewport(Viewport viewport) {
        // ignore
    }

    private String findChannel(int value) {
        WiFiChannel wiFiChannel = wiFiBand.getWiFiChannels().findWiFiChannel(value, bounds);
        if (wiFiChannel == WiFiChannel.UNKNOWN) {
            wiFiChannel = wiFiBand.getWiFiChannels().findWiFiChannelInRange(value, bounds);
        }
        if (wiFiChannel == WiFiChannel.UNKNOWN) {
            return StringUtils.EMPTY;
        }
        Locale locale = resources.getConfiguration().locale;
        int channel = wiFiChannel.getChannel();
        if (!WiFiChannelCountry.isChannelAvailable(locale, wiFiBand.isGHZ_5(), channel)) {
            return StringUtils.EMPTY;
        }
        return "" + channel;
    }

}
