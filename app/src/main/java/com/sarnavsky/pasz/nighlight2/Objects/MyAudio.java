package com.sarnavsky.pasz.nighlight2.Objects;

public class MyAudio {
   String name;

   String auth;
   String link;
   Boolean status = false;

   int linkId;


   public void setAuth(String auth) {
      this.auth = auth;
   }

   public String getAuth() {
      return auth;
   }

   public void setLinkId(int linkId) {
      this.linkId = linkId;
   }



   public void setLink(String link) {
      this.link = link;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setStatus(Boolean status) {
      this.status = status;
   }

   public Boolean getStatus() {
      return status;
   }

   public String getLink() {
      return link;
   }

   public String getName() {
      return name;
   }

   public int getLinkId() {
      return linkId;
   }
}
