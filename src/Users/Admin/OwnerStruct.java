package Users.Admin;

import DataBaseAcces.Entities.User;
import lombok.Data;

@Data
public class OwnerStruct {
    private User user;
    private int complaintsCount;
    private int formsCount;

    public OwnerStruct(User user, int complaintsCount, int formsCount)
    {
        this.user = user;
        this.complaintsCount = complaintsCount;
        this.formsCount = formsCount;
    }
}
