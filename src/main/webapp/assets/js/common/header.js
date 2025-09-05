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

document.addEventListener("DOMContentLoaded", () => {
  const logoImg = document.getElementById("logo-img");
  if (!logoImg) return;

  const normalSrc = logoImg.dataset.src || logoImg.src;
  const hoverSrc  = logoImg.dataset.hoverSrc || normalSrc;

  logoImg.addEventListener("mouseover", () => { logoImg.src = hoverSrc; });
  logoImg.addEventListener("mouseout",  () => { logoImg.src = normalSrc; });

  // 디버깅에 도움
  logoImg.onerror = () => console.warn("Logo image not found:", logoImg.src);
});