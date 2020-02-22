package by.epam.jwd.yakovlev.textparser.dao.impl;

import by.epam.jwd.yakovlev.textparser.dao.exception.DAOLogicException;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class DAOLogicImplTest {

    private static final DAOLogicImpl DAO = new DAOLogicImpl();
    private static final String FILENAME =
            "src\\test\\java\\by\\epam\\jwd\\yakovlev\\textparser\\sourcefile\\SourceText.txt";

    @Test
    public void readTextFromSourceFilePositiveTest() {

        String expected = "\tIt has survived - not only (five) centuries, but also the leap into 13<<2 electronic type setting, remaining 3>>5 essentially ~6&9|(3&4) unchanged. It was popularised in the 5|(1&2&(3|(4&(6^5|6&47)|3)|2)|1) with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                "\tIt is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78 Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content here), content here', making it look like readable English.\n" +
                "\tIt is a (4^5|1&2<<(2|5>>2&71))|1200 established fact that a reader will be of a page when looking at its layout.\n" +
                "\tBye.\n";

        String actual = null;

        try {
            actual = DAO.readTextFromSourceFile(FILENAME);
        } catch (DAOLogicException e) {
            Assert.fail(e.getMessage());
        }

        Assert.assertNotNull(actual);

        Assert.assertTrue(expected.equals(actual));

    }
}