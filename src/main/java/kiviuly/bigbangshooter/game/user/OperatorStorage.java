package kiviuly.bigbangshooter.game.user;

import kiviuly.bigbangshooter.game.Storage;

public class OperatorStorage extends Storage<Operator, String>
{
    private static OperatorStorage instance;
    public static OperatorStorage getInstance() {return instance;}

    public OperatorStorage(String folderName)
    {
        super(folderName);
        instance = this;
    }

    @Override
    public void Load()
    {

    }

    @Override
    public void Add(Operator op) {}
    @Override
    public Operator Get(String key) {return null;}
}