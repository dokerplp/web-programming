package dokerplp.jsf.bean;

import dokerplp.jsf.proxy.ResponseService;
import dokerplp.jsf.util.AreaCheckUtil;
import dokerplp.jsf.util.ValidationUtil;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Named("dataBean")
@SessionScoped
@Getter
@Setter
public class DataBean implements Serializable {

    private static final long serialVersionUID = 1;

    private final ResponseService service = new ResponseService();

    private Float x = 0f;

    private Float y;
    private Float r;

    private boolean r1, r2, r3, r15, r25;

    private String yt;

    private String yr;
    private String rr;

    private String img = "../img/graph0.png";

    public void setYt(String s) {
        this.yt = s;

        if (ValidationUtil.checkY(s)) {
            yr = "";
            y = Float.parseFloat(s);
        }
        else {
            yr = "*Please input correct \"Y\" value";
            y = null;
        }
    }

    public void setR1(boolean r1) {
        this.r1 = r1;
        checkR();
    }

    public void setR15(boolean r15) {
        this.r15 = r15;
        checkR();
    }

    public void setR2(boolean r2) {
        this.r2 = r2;
        checkR();
    }

    public void setR25(boolean r25) {
        this.r25 = r25;
        checkR();
    }

    public void setR3(boolean r3) {
        this.r3 = r3;
        checkR();
    }

    private void checkR(){
        Boolean[] arr = {r1, r2, r3, r15, r25};
        long c = Arrays.stream(arr).filter(el -> el).count();
        if (c == 0) {
            rr = "*Please choose one option";
            r = null;
            img = "../img/graph0.png";
        }
        else if (c != 1) {
            rr = "*Please choose ONLY ONE option";
            r = null;
            img = "../img/graph0.png";
        }
        else {
            rr = "";
            if (r1) {
                r = 1f;
                img = "../img/graph1.png";
            }
            else if (r15) {
                r = 1.5f;
                img = "../img/graph15.png";
            }
            else if (r2) {
                r = 2f;
                img = "../img/graph2.png";
            }
            else if (r25) {
                r = 2.5f;
                img = "../img/graph25.png";
            }
            else {
                r = 3f;
                img = "../img/graph3.png";
            }
        }

    }

    private Float hx, hy;

    public void submit(){
        long start = System.nanoTime();
        ResponseBean bean = new ResponseBean();
        if (hy != null && hx != null && r != null) {
            bean.setX(hx);
            bean.setY(hy);
            bean.setR(r);

            bean.setRes(AreaCheckUtil.isIn(hx, hy, r));
            bean.setLeft(AreaCheckUtil.left(hx));
            bean.setTop(AreaCheckUtil.top(hy));

            bean.setExTime((System.nanoTime() - start) / 1000);

            service.insert(bean);

            hy = null;
            hx = null;

        } else if (y != null && r != null) {
            bean.setX(x);
            bean.setY(y);
            bean.setR(r);

            bean.setRes(AreaCheckUtil.isIn(x, y, r));
            bean.setLeft(AreaCheckUtil.left(x));
            bean.setTop(AreaCheckUtil.top(y));

            bean.setExTime((System.nanoTime() - start) / 1000);

            service.insert(bean);
        }
    }

    public List<ResponseBean> select() {
        List<ResponseBean> list = service.selectAll();
        if (list != null) list.sort(Comparator.comparingInt(ResponseBean::getId));
        return list;
    }

    public void clear(){
        service.deleteAll();
    }

}
