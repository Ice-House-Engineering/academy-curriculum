# Security

Security is a complex topic. In this document, we just cover the summary of the security. Also, we provide the references for security topic in depth.

### References

https://mobile-security.gitbook.io/mobile-security-testing-guide/android-testing-guide/0x05a-platform-overview

https://developer.android.com/topic/security/best-practices

http://www.jssec.org/dl/android_securecoding_en.pdf

## Inside Application

Ask for credential when doing sensitive operations like paying something, or showing private key of cryptocurrencies.

Avoid storing sensitive data as much as possible because data is liabilities.

Instead of storing username and password, store the authentication token.

Limit permissions as much as possible. Use scoped directory access. If our application only needs to access a certain directory, we can limit the access of our application to that particular directory.

## Between Applications

The communication between our application and other applications must be secure. We can not leak information to other application unless it is important for the application.

Our application can launch another applications with Intent. Intent can be categorized to two categories: explicit and intent. Explicit intent choose which Activity to be launched. Implicit intent does not. For example: opening a URL. In that case, we have to give options for user to choose which application to open this URL. Which browsers. Which applications.

ContentProvider enables us to let other applications to get data from our application. If we don’t need it, we should disable it.

When we want to send information to public Activity, and if the information is sensitive like email, we need to use “putExtra” method.

Notification should not contain sensitive information. If it contains sensitive information, the setting of notification must be private so it does not show the message of the notification in the lock screen.

Always validate input especially if this input is sent to Activities or SQLite.

It’s better to outsource the responsibility to other application. For example, rather than updating contacts directly within the application, it’s better to launch Contact application.

## Network

Always use SSL. Don’t execute Javascript unless we know that Javascript source is secure. Always validate input from network.

## Data

Save data in Internal Storage and the permission of the file must exclude the world. Don’t save data in external storage unless it is being encrypted.

Don’t load code dynamically especially if the code is from outside (external storage).

Don’t store sensitive data in cache.

Use EncryptedSharedPreferences to save sensitive preferences.

## Code

Update all dependencies.

Learn to utilize the cryptography to encrypt or make sure the authenticity of the data.

Use static analyzers, vulnerability scanners, dynamic analysis tools, penetration testing. The list of these tools can be found here:  https://github.com/ashishb/android-security-awesome

## Libraries

Google provide libraries such as reCAPTCHA, Safe Browsing, Android Device Verification API, Verify Apps to increase security. These libraries can mitigate bot problems and avoid harmful URLs in our application.