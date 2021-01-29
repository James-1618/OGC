package util;

import njupt.domain.BoundingBox;

public class CalSim {
    /**
     * @param inputBoundingBox 鐢ㄦ埛鎻愪緵鐨勬暟鎹瀯寤烘垚鐨刡oundingbox
     * @param boundingBox      涓庣敤鎴锋彁渚涚殑鏁版嵁鏋勫缓鎴愮殑boundingbox杩涜鍖归厤姣旇緝鐨刡oundingbox
     * @return
     */
    public static double calSim(BoundingBox inputBoundingBox, BoundingBox boundingBox) {
        double iminx = inputBoundingBox.getMinx();
        double iminy = inputBoundingBox.getMiny();
        double imaxx = inputBoundingBox.getMaxx();
        double imaxy = inputBoundingBox.getMaxy();
        double minx = boundingBox.getMinx();
        double miny = boundingBox.getMiny();
        double maxx = boundingBox.getMaxx();
        double maxy = boundingBox.getMaxy();
        double iarea = (imaxx - iminx) * (imaxy - iminy);
        double area = (maxx - minx) * (maxy - miny);

        if (iminy > maxy || imaxx < minx || iminx > maxx || imaxy < miny) {
            // 涓や釜boundingbox涓嶇浉浜�
            return 0;
        } else {
            // 涓や釜boundingbox鐩镐氦
            if (iminx < maxx && maxx <= imaxx && minx < iminx) {
                // 姣旇緝鐨刡oundingbox鐨刴axx鍦ㄨ緭鍏ョ殑boundingbox鐨刴inx鍜宮axx涔嬮棿
                if (imaxy >= maxy && iminy <= miny) {
                    // 杈撳叆鐨刡oundingbox鐨刴axy澶т簬姣旇緝boundingbox鐨刴axy锛岃緭鍏ョ殑boundingbox鐨刴iny灏忎簬姣旇緝boundingbox鐨刴iny
                    return ((maxy - miny) * (maxx - iminx) / iarea + (maxy - miny) * (maxx - iminx) / area) / 2;
                } else if (imaxy <= maxy && iminy >= miny) {
                    // 杈撳叆鐨刡oundingbox鐨刴axy灏忎簬姣旇緝boundingbox鐨刴axy锛岃緭鍏ョ殑boundingbox鐨刴iny澶т簬姣旇緝boundingbox鐨刴iny
                    return ((imaxy - iminy) * (maxx - iminy) / iarea + (imaxy - iminy) * (maxx - iminy) / area) / 2;
                } else if (iminy < maxy && maxy <= imaxy && miny <= iminy) {
                    // 姣旇緝鐨刡oundingbox鐨刴axy鍦ㄨ緭鍏ョ殑boundingbox鐨刴iny鍜宮axy涔嬮棿
                    return ((maxy - iminy) * (maxx - iminy) / iarea + (maxy - iminy) * (maxx - iminy) / area) / 2;
                } else if (iminy <= miny && miny < imaxy && maxy >= imaxy) {
                    // 姣旇緝鐨刡oundingbox鐨刴iny鍦ㄨ緭鍏ョ殑boundingbox鐨刴iny鍜宮axy涔嬮棿
                    return ((imaxy - miny) * (maxx - iminy) / iarea + (imaxy - miny) * (maxx - iminy) / area) / 2;
                }

            } else if (minx <= iminx && imaxx <= maxx) {
                // 杈撳叆鐨刡oundingbox鐨刴inx澶т簬姣旇緝boundingbox鐨刴inx锛岃緭鍏ョ殑boundingbox鐨刴axx灏忎簬姣旇緝boundingbox鐨刴axx
                if (imaxy >= maxy && iminy <= miny) {
                    // 杈撳叆鐨刡oundingbox鐨刴axy澶т簬姣旇緝boundingbox鐨刴axy锛岃緭鍏ョ殑boundingbox鐨刴iny灏忎簬姣旇緝boundingbox鐨刴iny
                    return ((maxy - miny) * (imaxx - iminx) / iarea + (maxy - miny) * (imaxx - iminx) / area) / 2;
                } else if (imaxy <= maxy && iminy >= miny) {
                    // 杈撳叆鐨刡oundingbox鐨刴axy灏忎簬姣旇緝boundingbox鐨刴axy锛岃緭鍏ョ殑boundingbox鐨刴iny澶т簬姣旇緝boundingbox鐨刴iny
                    return ((imaxy - iminy) * (imaxx - iminx) / iarea + (imaxy - iminy) * (imaxx - iminx) / area) / 2;
                } else if (iminy < maxy && maxy <= imaxy && miny <= iminy) {
                    // 姣旇緝鐨刡oundingbox鐨刴axy鍦ㄨ緭鍏ョ殑boundingbox鐨刴iny鍜宮axy涔嬮棿
                    return ((maxy - iminy) * (imaxx - iminx) / iarea + (maxy - iminy) * (imaxx - iminx) / area) / 2;
                } else if (iminy <= miny && miny < imaxy && maxy >= imaxy) {
                    // 姣旇緝鐨刡oundingbox鐨刴iny鍦ㄨ緭鍏ョ殑boundingbox鐨刴iny鍜宮axy涔嬮棿
                    return ((imaxy - miny) * (imaxx - iminx) / iarea + (imaxy - miny) * (imaxx - iminx) / area) / 2;
                }

            } else if (iminx <= minx && minx < imaxx && maxx > imaxx) {
                // 姣旇緝鐨刡oundingbox鐨刴inx鍦ㄨ緭鍏ョ殑boundingbox鐨刴inx鍜宮axx涔嬮棿
                if (imaxy >= maxy && iminy <= miny) {
                    // 杈撳叆鐨刡oundingbox鐨刴axy澶т簬姣旇緝boundingbox鐨刴axy锛岃緭鍏ョ殑boundingbox鐨刴iny灏忎簬姣旇緝boundingbox鐨刴iny
                    return ((maxy - miny) * (imaxx - minx) / iarea + (maxy - miny) * (imaxx - minx) / area) / 2;
                } else if (imaxy <= maxy && iminy >= miny) {
                    // 杈撳叆鐨刡oundingbox鐨刴axy灏忎簬姣旇緝boundingbox鐨刴axy锛岃緭鍏ョ殑boundingbox鐨刴iny澶т簬姣旇緝boundingbox鐨刴iny
                    return ((imaxy - iminy) * (imaxx - minx) / iarea + (imaxy - iminy) * (imaxx - minx) / area) / 2;
                } else if (iminy < maxy && maxy <= imaxy && miny <= iminy) {
                    // 姣旇緝鐨刡oundingbox鐨刴axy鍦ㄨ緭鍏ョ殑boundingbox鐨刴iny鍜宮axy涔嬮棿
                    return ((maxy - iminy) * (imaxx - minx) / iarea + (maxy - iminy) * (imaxx - minx) / area) / 2;
                } else if (iminy <= miny && miny < imaxy && maxy >= imaxy) {
                    // 姣旇緝鐨刡oundingbox鐨刴iny鍦ㄨ緭鍏ョ殑boundingbox鐨刴iny鍜宮axy涔嬮棿
                    return ((imaxy - miny) * (imaxx - minx) / iarea + (imaxy - miny) * (imaxx - minx) / area) / 2;
                }

            } else if (iminx <= minx && maxx <= imaxx) {
                // 杈撳叆鐨刡oundingbox鐨刴inx灏忎簬姣旇緝boundingbox鐨刴inx锛岃緭鍏ョ殑boundingbox鐨刴axx澶т簬姣旇緝boundingbox鐨刴axx
                if (imaxy >= maxy && iminy <= miny) {
                    // 杈撳叆鐨刡oundingbox鐨刴axy澶т簬姣旇緝boundingbox鐨刴axy锛岃緭鍏ョ殑boundingbox鐨刴iny灏忎簬姣旇緝boundingbox鐨刴iny
                    return ((maxy - miny) * (maxx - minx) / iarea + (maxy - miny) * (maxx - minx) / area) / 2;
                } else if (imaxy <= maxy && iminy >= miny) {
                    // 杈撳叆鐨刡oundingbox鐨刴axy灏忎簬姣旇緝boundingbox鐨刴axy锛岃緭鍏ョ殑boundingbox鐨刴iny澶т簬姣旇緝boundingbox鐨刴iny
                    return ((imaxy - iminy) * (maxx - minx) / iarea + (imaxy - iminy) * (maxx - minx) / area) / 2;
                } else if (iminy < maxy && maxy <= imaxy && miny <= iminy) {
                    // 姣旇緝鐨刡oundingbox鐨刴axy鍦ㄨ緭鍏ョ殑boundingbox鐨刴iny鍜宮axy涔嬮棿
                    return ((maxy - iminy) * (maxx - minx) / iarea + (maxy - iminy) * (maxx - minx) / area) / 2;
                } else if (iminy <= miny && miny < imaxy && maxy >= imaxy) {
                    // 姣旇緝鐨刡oundingbox鐨刴iny鍦ㄨ緭鍏ョ殑boundingbox鐨刴iny鍜宮axy涔嬮棿
                    return ((imaxy - miny) * (maxx - minx) / iarea + (imaxy - miny) * (maxx - minx) / area) / 2;
                }
            }
        }
        return -1;
    }
}
