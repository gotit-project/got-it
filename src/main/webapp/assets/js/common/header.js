/* ================================
 * 세션이 존재한다면 login 없다면 default
 ================================== */
document.addEventListener("DOMContentLoaded", () => {
  const header = document.querySelector("header");
  if (!header) return;

  const loggedIn = header.dataset.loggedIn === "true";

  if (loggedIn) {
    header.id = "login";
  } else {
    header.id = "default";
  }
});