import { Tokens } from "@/types/Token";

const LS_KEY = "auth/tokens";
/**
 * Kiểm tra đang chạy trong trình duyệt?
 */
function isBrower(){
    return typeof window !== "undefined";
}

export function saveTokens(t:Tokens){
    if(!isBrower())return;
    localStorage.setItem(LS_KEY,JSON.stringify(t));
}
export function getTokens(): Tokens | null {
    if (!isBrower()) return null;
  
    const raw = localStorage.getItem(LS_KEY);
    if (!raw) return null;
  
    try {
      return JSON.parse(raw) as Tokens;
    } catch {
      return null;
    }
  }
  export function clearTokens() {
    if (!isBrower()) return;
    localStorage.removeItem(LS_KEY);
  }
  