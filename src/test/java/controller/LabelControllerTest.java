//package controller;
//
//import controller.LabelController;
//import model.Label;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.MockitoAnnotations;
//import repository.Impl.JdbcLabelRepositoryImpl;
//import repository.LabelRepository;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
//import static jdk.jfr.internal.jfc.model.Constraint.any;
//import static org.junit.Assert.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.Mockito.when;
//
//@RunWith(MockitoToJUnitRunner.class)
//public class LabelControllerTest {
//
//    @Mock
//    private LabelRepository labelRepository = (JdbcLabelRepositoryImpl.class);
//    @InjectMocks
//    private LabelController labelController = new LabelController(labelRepository);
//    private Label labelOne;
//    private Label label;
//
//    @Before
//    public void init() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void createLabel() {
//        label = new Label();
//
//        label.setId(1);
//        label.setName("germanphilosophy");
//
//        when(labelRepository.save(Constraint.any(Label.class))).thenReturn(label);
//
//        Label createLabel = LabelController.createLabel("label");
//        Assert.assertNotNull(createLabel);
//        Assert.assertEquals("label", createLabel.getName());
//    }
//
//    @Test
//    public void updateLabel() {
//
//        label = new Label();
//
//        label.setId(1);
//        label.setName("germanphilosophy");
//
//        when(labelRepository.update(any(Label.class))).thenReturn(label);
//        Assert.assertEquals("philosophy", label.getName());
//    }
//
//    @Test
//    public void getAll() {
//        labelOne = new Label();
//        label = new Label();
//
//        labelOne.setName("germanphilosophy");
//        label.setName("philisophy");
//
//        List<Label> labelList = new ArrayList<>();
//
//        labelList.add(labelOne);
//        labelList.add(label);
//
//        when(labelRepository.getAll()).thenReturn(labelList);
//        Assert.assertNotNull(labelList);
//    }
//
//    @Test
//    public void deleteByIdLabel() {
//        labelRepository.deleteById(anyInt());
//
//        verify(labelRepository).deleteById(anyInt());
//    }
//
//    @Test
//    public void getById() {
//
//        List<Label> labelList = new ArrayList<>();
//
//        labelList.add(labelOne);
//        labelList.add(label);
//
//        when(LabelRepository.getById(1)).thenReturn(labelOne);
//    }
//}
