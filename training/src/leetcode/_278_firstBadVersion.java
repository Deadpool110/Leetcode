package leetcode;

public class _278_firstBadVersion {
    VersionControl vc = new VersionControl();
//    二分查找
    public int firstBadVersion(int n) {
        int l = 1, r = n;
        while (l < r) {
            int mid = (r - l) / 2 + l;
            if (vc.isBadVersion(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}

class VersionControl {
    int v;

    public VersionControl() {}

    public VersionControl(int v) {
        this.v = v;
    }

    boolean isBadVersion(int version) {
        return version >= v;
    }
}
