# User Cases

**Background:**
Alex wants to connect to their FTP site to view their stored photos

## Connection

#### Use Case: Signing in
Alex has not signed into the app yet. They are greeted with the Sign In Screen, where they see the **FTP Site**, **Username** and **Password** entry fields. Plus the **Sign In** button. Alex taps on the **FTP Site** field, and the keyboard appears. The **Sign In** button appears above the keyboard, but is *disabled*. As Alex enters the text it appears in the field without validation. Alex then presses next on the keyboard and the cursor moves to the **Username** field. As Alex enters the text it appears in the field without validation. Alex then taps on the **Password** field and starts entering their password. As Alex enters the text it appears as stars to keep privacy. Once all three fields are filled, the **Sign In** button becomes enabled, and Alex presses it. A **Loading Spinner** appears and after some time Alex is navigated to the Browsing screen.

#### Use Case: Error
Alex has entered their information and tapped the **Sign In** button, however their information was wrong. An **Error View** appears above the **FTP Site** field, with text explaining what the issue is. Once Alex starts typing the **Error View** disappears.

#### Use Case: Viewing the Password
Alex has entered their password, but are unsure if they entered it correctly. They tap on the **Hide/Show** button at the right side of the field, the button changes to say *Hide* and the text changes from stars to the real text. Once Alex has confirmed their password, they tap the **Hide/Show** button again and the button changes to say *Show*.
