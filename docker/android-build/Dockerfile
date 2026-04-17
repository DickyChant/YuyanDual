# Reproducible Android build image aligned with .github/workflows/android-build.yml (Temurin 17 + SDK).
FROM eclipse-temurin:17-jdk-jammy

ENV ANDROID_HOME=/opt/android-sdk \
    ANDROID_SDK_ROOT=/opt/android-sdk \
    PATH="${PATH}:/opt/android-sdk/cmdline-tools/latest/bin:/opt/android-sdk/platform-tools"

RUN apt-get update \
    && DEBIAN_FRONTEND=noninteractive apt-get install -y --no-install-recommends \
        wget unzip ca-certificates git \
    && rm -rf /var/lib/apt/lists/*

# https://developer.android.com/studio#command-tools
ARG CMDLINE_TOOLS_ZIP=commandlinetools-linux-11076708_latest.zip
RUN mkdir -p "${ANDROID_HOME}/cmdline-tools" \
    && wget -q "https://dl.google.com/android/repository/${CMDLINE_TOOLS_ZIP}" -O /tmp/cmdline-tools.zip \
    && unzip -q /tmp/cmdline-tools.zip -d /tmp \
    && mv /tmp/cmdline-tools "${ANDROID_HOME}/cmdline-tools/latest" \
    && rm -f /tmp/cmdline-tools.zip

RUN yes | sdkmanager --licenses >/dev/null \
    && sdkmanager --install \
        "platform-tools" \
        "platforms;android-36" \
        "build-tools;35.0.0"

WORKDIR /workspace
