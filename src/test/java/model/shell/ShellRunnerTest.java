package model.shell;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * ShellRunner 测试
 * author JessySnow
*/
public class ShellRunnerTest{
    
    ShellRunner runner = new ShellRunner();

    @Test
    public void rightTest(){
        var rightRet = runner.run("whoami", "");
        assertEquals(rightRet.isErrorOccur(), false);
    }

    @Test
    public void wrongTest(){
        var wrongRet = runner.run("whioaaa", "");
        assertEquals(wrongRet.isErrorOccur(), true);
    }
}