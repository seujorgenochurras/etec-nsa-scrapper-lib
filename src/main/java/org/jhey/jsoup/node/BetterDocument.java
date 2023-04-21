package org.jhey.jsoup.node;

import java.nio.charset.Charset;
import java.util.Objects;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class BetterDocument {
   private final Document baseDocument;

   public BetterDocument( Document baseDocument) {
      this.baseDocument = baseDocument;
   }


   public Document getWithEncodedString(Charset charsetEncoder){
      return Jsoup.parse(new String(this.baseDocument.html().getBytes(), charsetEncoder));
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof BetterDocument that)) return false;
      if (!super.equals(o)) return false;

      return Objects.equals(baseDocument, that.baseDocument);
   }

   @Override
   public int hashCode() {
      int result = super.hashCode();
      result = 31 * result + (baseDocument != null ? baseDocument.hashCode() : 0);
      return result;
   }
}
