package org.jhey.jsoup;

import java.util.HashMap;

public class NsaCookies extends HashMap<String, String> {
   public NsaCookies(String sessionCookieToken, String arrAffinity, String arrAffinitySameSite) {
      this.put("ARRAffinity", arrAffinity);
      this.put("ARRAffinitySameSite", arrAffinitySameSite);
      this.put("NSA_OnLine_SessionId", sessionCookieToken);
   }

   public NsaCookies() {
   }

   public static NsaCookies emptyCookies(){
      return new NsaCookies();
   }
}
