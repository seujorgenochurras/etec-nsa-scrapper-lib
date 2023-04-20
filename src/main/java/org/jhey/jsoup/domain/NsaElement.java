package org.jhey.jsoup.domain;

import org.jetbrains.annotations.NotNull;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.util.*;

public class NsaElement {
   private Element rootElement;

   public NsaElement(Element rootElement) {
      this.rootElement = rootElement;
   }

   public boolean hasNoText(){
      final String emptyCharInHtml = "&nbsp;";
      return this.rootElement.wholeText().isBlank() || this.rootElement.toString().contains(emptyCharInHtml);
   }

   public Element getRootElement() {
      return rootElement;
   }

   /**
    * Garbage means empty element and duplicates <br>
    * This also removes all the attributes of the element
    * @return {@code Element} containing only those elements that are not duplicates and empty
    */
   public Element removeHtmlGarbage(){
      ElementSet cleanElementsSet = new ElementSet(rootElement.getAllElements());

      cleanElementsSet.removeEmptyAndDuplicatedElements();
      cleanElementsSet.removeAllAttributes();

      rootElement = cleanElementsSet.toElement();

      return rootElement;
   }

   @Override
   public String toString() {
      return rootElement.toString();
   }

   private static class ElementSet extends LinkedHashSet<Element>{
      public ElementSet(@NotNull Collection<? extends Element> c) {
         super(c);
      }

      /**
       * @return returns this collection as one Element
       */
      public Element toElement(){
         Element resultedElement = (Element) this.toArray()[0];

         Elements collectionAsElements = new Elements();
         collectionAsElements.addAll(this);
         resultedElement.remove();
         for (Element element : collectionAsElements) {
            resultedElement.appendElement(element.toString());
         }
         return resultedElement;
      }
      public void removeAllAttributes(){
         this.forEach(element ->{
            element.clearAttributes();
            for (Node node : element.childNodes()) {
               node.clearAttributes();
            }
         });
      }

      private void removeEmptyAndDuplicatedElements(){
         Set<Element> notEmptyElements = new LinkedHashSet<>();
         this.forEach(element -> {
            if(!(new NsaElement(element).hasNoText())) {
               notEmptyElements.add(element);
            }
         });
         this.clear();
         this.addAll(notEmptyElements);
      }
   }
}
