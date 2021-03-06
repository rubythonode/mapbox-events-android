package com.mapbox.services.android.telemetry;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.JsonAdapter;

class NavigationRerouteEvent extends Event implements Parcelable {
  @JsonAdapter(NavigationMetadataSerializer.class)
  private NavigationMetadata navigationMetadata;
  @JsonAdapter(RerouteDataSerializer.class)
  private NavigationRerouteData navigationRerouteData;
  @JsonAdapter(LocationDataSerializer.class)
  private NavigationLocationData navigationLocationData;
  @JsonAdapter(FeedbackDataSerializer.class)
  private FeedbackData feedbackData;
  @JsonAdapter(VoiceDataSerializer.class)
  private NavigationVoiceData voiceData;
  private NavigationStepMetadata step = null;

  NavigationRerouteEvent(NavigationState navigationState) {
    this.feedbackData = navigationState.getFeedbackData();
    this.voiceData = navigationState.getNavigationVoiceData();
    this.navigationMetadata = navigationState.getNavigationMetadata();
    this.navigationRerouteData = navigationState.getNavigationRerouteData();
    this.navigationLocationData = navigationState.getNavigationLocationData();
    this.step = navigationState.getNavigationStepMetadata();
  }

  @Override
  Type obtainType() {
    return Type.NAV_REROUTE;
  }

  NavigationLocationData getNavigationLocationData() {
    return navigationLocationData;
  }

  NavigationRerouteData getNavigationRerouteData() {
    return navigationRerouteData;
  }

  NavigationStepMetadata getStep() {
    return step;
  }

  NavigationVoiceData getVoiceData() {
    return voiceData;
  }

  FeedbackData getFeedbackData() {
    return feedbackData;
  }

  NavigationMetadata getNavigationMetadata() {
    return navigationMetadata;
  }

  private NavigationRerouteEvent(Parcel in) {
    navigationMetadata = in.readParcelable(NavigationMetadata.class.getClassLoader());
    navigationLocationData = in.readParcelable(NavigationLocationData.class.getClassLoader());
    feedbackData = in.readParcelable(FeedbackData.class.getClassLoader());
    voiceData = in.readParcelable(NavigationVoiceData.class.getClassLoader());
    step = in.readParcelable(NavigationStepMetadata.class.getClassLoader());
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeParcelable(navigationMetadata, flags);
    dest.writeParcelable(navigationLocationData, flags);
    dest.writeParcelable(feedbackData, flags);
    dest.writeParcelable(voiceData, flags);
    dest.writeParcelable(step, flags);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @SuppressWarnings("unused")
  public static final Creator<NavigationRerouteEvent> CREATOR = new Creator<NavigationRerouteEvent>() {
    @Override
    public NavigationRerouteEvent createFromParcel(Parcel in) {
      return new NavigationRerouteEvent(in);
    }

    @Override
    public NavigationRerouteEvent[] newArray(int size) {
      return new NavigationRerouteEvent[size];
    }
  };
}
